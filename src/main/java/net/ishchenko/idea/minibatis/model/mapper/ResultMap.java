package net.ishchenko.idea.minibatis.model.mapper;

import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;
import consulo.xml.dom.NameValue;

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
