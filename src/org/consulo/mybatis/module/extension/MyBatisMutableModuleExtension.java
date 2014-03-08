package org.consulo.mybatis.module.extension;

import javax.swing.JComponent;

import org.consulo.module.extension.MutableModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.roots.ModifiableRootModel;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisMutableModuleExtension extends MyBatisModuleExtension implements MutableModuleExtension<MyBatisModuleExtension>
{
	public MyBatisMutableModuleExtension(@NotNull String id, @NotNull ModifiableRootModel rootModel)
	{
		super(id, rootModel);
	}

	@Nullable
	@Override
	public JComponent createConfigurablePanel(@Nullable Runnable runnable)
	{
		return null;
	}

	@Override
	public void setEnabled(boolean b)
	{
		myIsEnabled = b;
	}

	@Override
	public boolean isModified(@NotNull MyBatisModuleExtension extension)
	{
		return myIsEnabled != extension.isEnabled();
	}
}
