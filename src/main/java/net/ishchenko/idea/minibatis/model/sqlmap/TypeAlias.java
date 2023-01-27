package net.ishchenko.idea.minibatis.model.sqlmap;

import com.intellij.java.language.psi.PsiClass;
import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.NameValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.01.12
 * Time: 21:50
 */
public interface TypeAlias extends DomElement
{
	@NameValue
	@Attribute("alias")
	GenericAttributeValue<String> getAlias();

	@Attribute("type")
	GenericAttributeValue<PsiClass> getType();
}
