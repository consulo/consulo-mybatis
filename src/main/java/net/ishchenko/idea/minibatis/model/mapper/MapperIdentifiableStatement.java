package net.ishchenko.idea.minibatis.model.mapper;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.NameValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 23.12.11
 * Time: 23:43
 */
public interface MapperIdentifiableStatement extends DomElement, WithIncludes
{
	@NameValue
	@Attribute("id")
	GenericAttributeValue<String> getId();
}
