package net.ishchenko.idea.minibatis;

import net.ishchenko.idea.minibatis.model.mapper.Mapper;

import com.intellij.util.xml.DomFileDescription;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 01.01.12
 * Time: 18:38
 */
public class MapperDescription extends DomFileDescription<Mapper>
{
	public MapperDescription()
	{
		super(Mapper.class, "mapper");
	}
}
