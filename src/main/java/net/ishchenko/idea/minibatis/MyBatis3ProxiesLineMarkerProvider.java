package net.ishchenko.idea.minibatis;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiIdentifier;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.AllIcons;
import consulo.application.util.function.CommonProcessors;
import consulo.codeEditor.markup.GutterIconRenderer;
import consulo.ide.ServiceManager;
import consulo.language.Language;
import consulo.language.editor.Pass;
import consulo.language.editor.gutter.GutterIconNavigationHandler;
import consulo.language.editor.gutter.LineMarkerInfo;
import consulo.language.editor.gutter.LineMarkerProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiNameIdentifierOwner;
import consulo.navigation.Navigatable;
import consulo.xml.psi.xml.XmlElement;
import consulo.xml.util.xml.DomElement;

import jakarta.annotation.Nonnull;
import java.awt.event.MouseEvent;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.01.12
 * Time: 15:45
 */
@ExtensionImpl
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

		return new Function<PsiIdentifier, String>()
		{
			@Override
			public String apply(PsiIdentifier psiIdentifier)
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

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JavaLanguage.INSTANCE;
	}
}
