package org.consulo.mybatis.module.extension;

import javax.swing.Icon;

import org.consulo.module.extension.ModuleExtensionProvider;
import org.consulo.mybatis.MyBatisIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;

/**
 * @author VISTALL
 * @since 19:54/27.06.13
 */
public class MyBatisModuleExtensionProvider implements ModuleExtensionProvider<MyBatisModuleExtension, MyBatisMutableModuleExtension>
{
	@Nullable
	@Override
	public Icon getIcon()
	{
		return MyBatisIcons.MyBatis;
	}

	@NotNull
	@Override
	public String getName()
	{
		return "iBatis/MyBatis";
	}

	@NotNull
	@Override
	public MyBatisModuleExtension createImmutable(@NotNull String s, @NotNull Module module)
	{
		return new MyBatisModuleExtension(s, module);
	}

	@NotNull
	@Override
	public MyBatisMutableModuleExtension createMutable(@NotNull String s, @NotNull Module module, @NotNull MyBatisModuleExtension moduleExtension)
	{
		return new MyBatisMutableModuleExtension(s, module, moduleExtension);
	}
}
