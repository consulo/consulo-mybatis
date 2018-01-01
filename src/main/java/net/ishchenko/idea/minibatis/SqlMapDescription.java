package net.ishchenko.idea.minibatis;

import net.ishchenko.idea.minibatis.model.sqlmap.SqlMap;

import com.intellij.util.xml.DomFileDescription;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 24.12.11
 * Time: 0:00
 */
public class SqlMapDescription extends DomFileDescription<SqlMap>
{

	public SqlMapDescription()
	{
		super(SqlMap.class, "sqlMap");
	}

}
