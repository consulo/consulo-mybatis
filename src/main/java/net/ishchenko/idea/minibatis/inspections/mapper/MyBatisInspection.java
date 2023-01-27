package net.ishchenko.idea.minibatis.inspections.mapper;

import com.intellij.java.analysis.impl.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.java.language.JavaLanguage;
import consulo.language.Language;
import org.jetbrains.annotations.Nls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.06.12
 * Time: 11:47
 */
public abstract class MyBatisInspection extends BaseJavaLocalInspectionTool
{
	@Nullable
	@Override
	public Language getLanguage()
	{
		return JavaLanguage.INSTANCE;
	}

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
