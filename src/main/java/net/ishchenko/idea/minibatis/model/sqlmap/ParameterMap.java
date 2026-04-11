package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;
import consulo.xml.dom.NameValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.03.12
 * Time: 21:44
 */
public interface ParameterMap extends DomElement
{
	@NameValue
	@Attribute("id")
	GenericAttributeValue<String> getId();

	@Attribute("class")
	GenericAttributeValue<TypeAlias> getClazz();
}
