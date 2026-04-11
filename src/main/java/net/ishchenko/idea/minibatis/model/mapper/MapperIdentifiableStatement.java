package net.ishchenko.idea.minibatis.model.mapper;

import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;
import consulo.xml.dom.NameValue;

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
