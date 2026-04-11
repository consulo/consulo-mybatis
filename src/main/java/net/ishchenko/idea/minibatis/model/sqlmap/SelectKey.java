package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;

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
