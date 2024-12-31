package net.ishchenko.idea.minibatis;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiIdentifier;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.annotation.component.ServiceImpl;
import consulo.application.Application;
import consulo.application.util.function.CommonProcessors;
import consulo.application.util.function.Processor;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.project.Project;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.DomFileElement;
import consulo.xml.util.xml.DomService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.ishchenko.idea.minibatis.model.mapper.Mapper;
import net.ishchenko.idea.minibatis.model.mapper.MapperIdentifiableStatement;
import net.ishchenko.idea.minibatis.model.sqlmap.ResultMap;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMap;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMapIdentifiableStatement;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.01.12
 * Time: 16:03
 */
@Singleton
@ServiceAPI(ComponentScope.PROJECT)
@ServiceImpl
public class DomFileElementsFinder
{
	private final Project project;
	private final DomService domService;
	private final Application application;

	@Inject
	public DomFileElementsFinder(Project project, DomService domService, Application application)
	{
		this.project = project;
		this.domService = domService;
		this.application = application;
	}

	public void processSqlMapStatements(@Nonnull String targetNamespace, @Nonnull String targetId, @Nonnull Processor<? super SqlMapIdentifiableStatement> processor)
	{

		nsloop:
		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap sqlMap = fileElement.getRootElement();
			String namespace = sqlMap.getNamespace().getRawText();
			if(targetNamespace.equals(namespace) || targetNamespace.length() == 0)
			{
				for(SqlMapIdentifiableStatement statement : sqlMap.getIdentifiableStatements())
				{
					if(targetId.equals(statement.getId().getRawText()))
					{
						if(!processor.process(statement))
						{
							return;
						}
						continue nsloop;
					}
				}
			}
		}

	}

	public void processSqlMapStatementNames(@Nonnull Processor<String> processor)
	{

		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap rootElement = fileElement.getRootElement();
			String namespace = rootElement.getNamespace().getRawText();
			for(SqlMapIdentifiableStatement statement : rootElement.getIdentifiableStatements())
			{
				String id = statement.getId().getRawText();
				if(id != null && (namespace != null && !processor.process(namespace + "." + id) || namespace == null && !processor.process(id)))
				{
					return;
				}
			}
		}

	}

	public void processSqlMaps(@Nonnull String targetNamespace, @Nonnull Processor<? super SqlMap> processor)
	{

		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap sqlMap = fileElement.getRootElement();
			String namespace = sqlMap.getNamespace().getRawText();
			if(targetNamespace.equals(namespace))
			{
				if(!processor.process(sqlMap))
				{
					return;
				}
			}
		}

	}

	public void processSqlMapNamespaceNames(CommonProcessors.CollectProcessor<String> processor)
	{

		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap sqlMap = fileElement.getRootElement();
			if(sqlMap.getNamespace().getRawText() != null && !processor.process(sqlMap.getNamespace().getRawText()))
			{
				return;
			}
		}
	}

	public void processResultMaps(@Nonnull String targetNamespace, @Nonnull String targetId, @Nonnull Processor<? super ResultMap> processor)
	{

		nsloop:
		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap rootElement = fileElement.getRootElement();
			String namespace = rootElement.getNamespace().getRawText();
			if(targetNamespace.equals(namespace) || targetNamespace.length() == 0 && namespace == null)
			{
				for(ResultMap resultMap : rootElement.getResultMaps())
				{
					if(targetId.equals(resultMap.getId().getRawText()))
					{
						if(!processor.process(resultMap))
						{
							return;
						}
						continue nsloop;
					}
				}
			}

		}

	}

	public void processResultMapNames(@Nonnull Processor<String> processor)
	{

		for(DomFileElement<SqlMap> fileElement : findSqlMapFileElements())
		{
			SqlMap rootElement = fileElement.getRootElement();
			String namespace = rootElement.getNamespace().getRawText();
			for(ResultMap resultMap : rootElement.getResultMaps())
			{
				String id = resultMap.getId().getRawText();
				if(id != null && (namespace != null && !processor.process(namespace + "." + id) || namespace == null && !processor.process(id)))
				{
					return;
				}
			}
		}

	}

	public void processMappers(@Nonnull final PsiClass clazz, @Nonnull final Processor<? super Mapper> processor)
	{
		application.runReadAction(new Runnable()
		{
			@Override
			public void run()
			{
				if(clazz.isInterface())
				{
					PsiIdentifier nameIdentifier = clazz.getNameIdentifier();
					String qualifiedName = clazz.getQualifiedName();
					if(nameIdentifier != null && qualifiedName != null)
					{
						processMappers(qualifiedName, processor);
					}
				}
			}
		});
	}

	public void processMapperStatements(@Nonnull final PsiMethod method, @Nonnull final Processor<? super MapperIdentifiableStatement> processor)
	{

		application.runReadAction(new Runnable()
		{
			@Override
			public void run()
			{
				PsiClass clazz = method.getContainingClass();
				if(clazz != null && clazz.isInterface())
				{
					String qualifiedName = clazz.getQualifiedName();
					String methodName = method.getName();
					if(qualifiedName != null)
					{
						processMapperStatements(qualifiedName, methodName, processor);
					}
				}
			}
		});

	}

	public boolean existsMapperStatement(PsiMethod method)
	{
		CommonProcessors.FindFirstProcessor<DomElement> processor = new CommonProcessors.FindFirstProcessor<DomElement>();
		processMapperStatements(method, processor);
		return processor.isFound();
	}

	private void processMappers(String className, Processor<? super Mapper> processor)
	{
		for(DomFileElement<Mapper> fileElement : findMapperFileElements())
		{
			Mapper mapper = fileElement.getRootElement();
			if(className.equals(mapper.getNamespace().getRawText()))
			{
				if(!processor.process(mapper))
				{
					return;
				}
			}
		}
	}

	private void processMapperStatements(String className, String methodName, Processor<? super MapperIdentifiableStatement> processor)
	{
		for(DomFileElement<Mapper> fileElement : findMapperFileElements())
		{
			Mapper mapper = fileElement.getRootElement();
			if(className.equals(mapper.getNamespace().getRawText()))
			{
				for(MapperIdentifiableStatement statement : mapper.getIdentifiableStatements())
				{
					if(methodName.equals(statement.getId().getRawText()))
					{
						if(!processor.process(statement))
						{
							return;
						}
					}
				}
			}
		}
	}

	private List<DomFileElement<SqlMap>> findSqlMapFileElements()
	{
		return domService.getFileElements(SqlMap.class, project, GlobalSearchScope.allScope(project));
	}

	private List<DomFileElement<Mapper>> findMapperFileElements()
	{
		return domService.getFileElements(Mapper.class, project, GlobalSearchScope.allScope(project));
	}
}


