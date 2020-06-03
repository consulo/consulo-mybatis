package net.ishchenko.idea.minibatis.inspections.mapper;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nls;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.06.12
 * Time: 11:47
 */
public abstract class MyBatisInspection extends BaseJavaLocalInspectionTool
{

	@Nls
	@Nonnull
	@Override
	public String getGroupDisplayName()
	{
		return "iBATIS/MyBatis issues";
	}

	@Nonnull
	@Override
	public String getShortName()
	{
		return this.getClass().getName();
	}

}
