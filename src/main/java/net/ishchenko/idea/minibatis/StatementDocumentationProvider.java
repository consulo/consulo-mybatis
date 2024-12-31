package net.ishchenko.idea.minibatis;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.PsiJavaToken;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.util.function.CommonProcessors;
import consulo.ide.ServiceManager;
import consulo.language.Language;
import consulo.language.editor.documentation.LanguageDocumentationProvider;
import consulo.language.psi.PsiElement;
import consulo.util.lang.StringUtil;
import consulo.xml.psi.xml.XmlElement;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMapIdentifiableStatement;

import jakarta.annotation.Nonnull;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.02.12
 * Time: 22:01
 */
@ExtensionImpl
public class StatementDocumentationProvider implements LanguageDocumentationProvider
{
	private static final Pattern dotPattern = Pattern.compile("\\.");

	@Override
	public String generateDoc(PsiElement element, PsiElement originalElement)
	{

		if(originalElement instanceof PsiJavaToken)
		{

			String rawText = originalElement.getText();
			String[] parts = dotPattern.split(rawText.substring(1, rawText.length() - 1), 2);

			String namespace;
			String id;

			if(parts.length == 2)
			{
				namespace = parts[0];
				id = parts[1];
			}
			else
			{
				namespace = "";
				id = parts[0];
			}

			DomFileElementsFinder finder = ServiceManager.getService(originalElement.getProject(), DomFileElementsFinder.class);
			CommonProcessors.FindFirstProcessor<SqlMapIdentifiableStatement> processor = new CommonProcessors.FindFirstProcessor<SqlMapIdentifiableStatement>();
			finder.processSqlMapStatements(namespace, id, processor);
			if(processor.isFound())
			{
				SqlMapIdentifiableStatement statement = processor.getFoundValue();
				XmlElement xmlElement = statement.getXmlElement();
				if(xmlElement != null)
				{
					return "<pre>" + StringUtil.escapeXml(xmlElement.getText()) + "</pre>";
				}
			}

			return null;

		}
		return null;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JavaLanguage.INSTANCE;
	}
}
