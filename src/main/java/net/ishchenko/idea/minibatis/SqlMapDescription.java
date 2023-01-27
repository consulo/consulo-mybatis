package net.ishchenko.idea.minibatis;

import consulo.annotation.component.ExtensionImpl;
import consulo.xml.util.xml.DomFileDescription;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMap;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 24.12.11
 * Time: 0:00
 */
@ExtensionImpl
public class SqlMapDescription extends DomFileDescription<SqlMap>
{

	public SqlMapDescription()
	{
		super(SqlMap.class, "sqlMap");
	}

}
