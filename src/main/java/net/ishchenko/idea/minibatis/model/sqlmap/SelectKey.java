package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.03.12
 * Time: 21:58
 */
public interface SelectKey extends DomElement
{
	@Attribute("resultClass")
	GenericAttributeValue<TypeAlias> getResultClass();
}
