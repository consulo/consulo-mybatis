package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.NameValue;

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
