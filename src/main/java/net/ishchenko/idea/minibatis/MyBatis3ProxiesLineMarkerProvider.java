package net.ishchenko.idea.minibatis;

import java.awt.event.MouseEvent;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.xml.XmlElement;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Function;
import com.intellij.util.NullableFunction;
import com.intellij.util.xml.DomElement;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.01.12
 * Time: 15:45
 */
public class MyBatis3ProxiesLineMarkerProvider implements LineMarkerProvider
{
	@Override
	public LineMarkerInfo getLineMarkerInfo(PsiElement element)
	{

		if(element instanceof PsiNameIdentifierOwner)
		{

			DomFileElementsFinder finder = ServiceManager.getService(element.getProject(), DomFileElementsFinder.class);
			CommonProcessors.FindFirstProcessor<DomElement> processor = new CommonProcessors.FindFirstProcessor<DomElement>();

			if(element instanceof PsiClass)
			{
				finder.processMappers((PsiClass) element, processor);
			}
			else if(element instanceof PsiMethod)
			{
				finder.processMapperStatements((PsiMethod) element, processor);
			}

			PsiElement nameIdentifier = ((PsiNameIdentifierOwner) element).getNameIdentifier();
			if(processor.isFound() && nameIdentifier != null)
			{
				return new LineMarkerInfo<>((PsiIdentifier) nameIdentifier,
						nameIdentifier.getTextRange(), AllIcons.Gutter.ImplementedMethod, Pass.UPDATE_ALL, getTooltipProvider(processor.getFoundValue()), getNavigationHandler(processor.getFoundValue().getXmlElement()), GutterIconRenderer.Alignment.CENTER);
			}
		}

		return null;

	}

	private Function<PsiIdentifier, String> getTooltipProvider(final DomElement element)
	{

		return new NullableFunction<PsiIdentifier, String>()
		{
			@Override
			public String fun(PsiIdentifier psiIdentifier)
			{
				XmlElement xmlElement = element.getXmlElement();
				if(xmlElement != null)
				{
					return element.getXmlElementName() + " in " + xmlElement.getContainingFile().getName();
				}
				else
				{
					return null;
				}
			}
		};

	}

	private GutterIconNavigationHandler<PsiIdentifier> getNavigationHandler(final XmlElement statement)
	{
		return new GutterIconNavigationHandler<PsiIdentifier>()
		{
			@Override
			public void navigate(MouseEvent e, PsiIdentifier elt)
			{
				if(statement instanceof Navigatable)
				{
					((Navigatable) statement).navigate(true);
				}
				else
				{
					throw new AssertionError("Could not navigate statement " + statement);
				}
			}
		};
	}

}
