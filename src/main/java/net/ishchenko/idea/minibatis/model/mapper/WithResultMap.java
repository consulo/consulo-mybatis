package net.ishchenko.idea.minibatis.model.mapper;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;

/**
 * Created with IntelliJ IDEA.
 * User: mishchenko
 * Date: 26.05.12
 * Time: 16:20
 */
public interface WithResultMap extends DomElement
{

	@Attribute("resultMap")
	GenericAttributeValue<ResultMap> getResultMap();

}
