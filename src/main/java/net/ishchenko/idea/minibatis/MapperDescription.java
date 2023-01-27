package net.ishchenko.idea.minibatis;

import consulo.annotation.component.ExtensionImpl;
import consulo.xml.util.xml.DomFileDescription;
import net.ishchenko.idea.minibatis.model.mapper.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.01.12
 * Time: 18:38
 */
@ExtensionImpl
public class MapperDescription extends DomFileDescription<Mapper>
{
	public MapperDescription()
	{
		super(Mapper.class, "mapper");
	}
}
