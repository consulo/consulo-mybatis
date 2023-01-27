package net.ishchenko.idea.minibatis.model.mapper;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.NameValue;

/**
 * Created with IntelliJ IDEA.
 * User: mishchenko
 * Date: 26.05.12
 * Time: 16:21
 */
public interface ResultMap extends DomElement
{
	@NameValue
	@Attribute("id")
	GenericAttributeValue<String> getId();

	@Attribute("extends")
	GenericAttributeValue<ResultMap> getExtends();
}
