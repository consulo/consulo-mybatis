package net.ishchenko.idea.minibatis.model.sqlmap;

import com.intellij.java.language.psi.PsiClass;
import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;
import consulo.xml.dom.NameValue;

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
