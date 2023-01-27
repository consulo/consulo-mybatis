package net.ishchenko.idea.minibatis;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.language.editor.QualifiedNameProvider;
import consulo.language.pom.PomTarget;
import consulo.language.pom.PomTargetPsiElement;
import consulo.language.psi.PsiElement;
import consulo.project.Project;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.DomTarget;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMap;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMapIdentifiableStatement;

import javax.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Max
 * Date: 30.04.13
 * Time: 0:06
 */
@ExtensionImpl
public class IdentifiableStatementQualifiedNameProvider implements QualifiedNameProvider
{
	@Nullable
	@Override
	public PsiElement adjustElementToCopy(PsiElement element)
	{
		if(resolveIdentifiableStatement(element) != null)
		{
			return element;
		}
		else
		{
			return null;
		}
	}

	@Nullable
	@Override
	public String getQualifiedName(PsiElement element)
	{
		SqlMapIdentifiableStatement statement = resolveIdentifiableStatement(element);
		if(statement != null)
		{
			SqlMap sqlMap = statement.getParentOfType(SqlMap.class, true);
			if(sqlMap != null)
			{
				String namespace = sqlMap.getNamespace().getRawText();
				return (namespace != null ? namespace + "." : "") + statement.getId();
			}
		}
		return null;
	}

	@Override
	public PsiElement qualifiedNameToElement(String fqn, Project project)
	{
		return null;
	}

	@Override
	public void insertQualifiedName(String fqn, PsiElement element, Editor editor, Project project)
	{

	}

	private SqlMapIdentifiableStatement resolveIdentifiableStatement(PsiElement element)
	{
		if(element instanceof PomTargetPsiElement)
		{
			PomTarget target = ((PomTargetPsiElement) element).getTarget();
			if(target instanceof DomTarget)
			{
				DomElement domElement = ((DomTarget) target).getDomElement();
				if(domElement instanceof SqlMapIdentifiableStatement)
				{
					return (SqlMapIdentifiableStatement) domElement;
				}
			}
		}
		return null;
	}

}
