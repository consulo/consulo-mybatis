package net.ishchenko.idea.minibatis;

import consulo.application.util.function.CommonProcessors;
import consulo.ide.ServiceManager;
import consulo.language.impl.psi.PomTargetPsiElementImpl;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReferenceBase;
import consulo.xml.psi.xml.XmlElement;
import consulo.xml.util.xml.DomTarget;
import net.ishchenko.idea.minibatis.model.sqlmap.ResultMap;

import jakarta.annotation.Nonnull;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 22.04.12
 * Time: 10:03
 */
public class ResultMapReference extends PsiReferenceBase<PsiElement>
{
	private static final Pattern dotPattern = Pattern.compile("\\.");

	public ResultMapReference(@Nonnull PsiElement element)
	{
		super(element);
	}

	@Override
	public PsiElement resolve()
	{
		String rawText = getElement().getText();
		if(rawText.length() <= 2)
		{
			return null;
		}

		String[] split = dotPattern.split(rawText.substring(1, rawText.length() - 1), 2);

		String namespace;
		String id;

		if(split.length == 2)
		{
			namespace = split[0];
			id = split[1];
		}
		else
		{
			namespace = "";
			id = split[0];
		}

		CommonProcessors.FindFirstProcessor<ResultMap> processor = new CommonProcessors.FindFirstProcessor<ResultMap>();
		ServiceManager.getService(getElement().getProject(), DomFileElementsFinder.class).processResultMaps(namespace, id, processor);
		ResultMap foundValue = processor.getFoundValue();
		if(foundValue != null)
		{
			DomTarget target = DomTarget.getTarget(foundValue);
			if(target != null)
			{
				XmlElement xmlElement = foundValue.getXmlElement();
				final String locationString = xmlElement != null ? xmlElement.getContainingFile().getName() : "";
				return new PomTargetPsiElementImpl(target)
				{
					@Override
					public String getLocationString()
					{
						return locationString;
					}
				};
			}
		}

		return null;

	}

	@Nonnull
	@Override
	public Object[] getVariants()
	{
		CommonProcessors.CollectProcessor<String> processor = new CommonProcessors.CollectProcessor<String>();
		ServiceManager.getService(getElement().getProject(), DomFileElementsFinder.class).processResultMapNames(processor);
		return processor.toArray(new String[processor.getResults().size()]);
	}
}
