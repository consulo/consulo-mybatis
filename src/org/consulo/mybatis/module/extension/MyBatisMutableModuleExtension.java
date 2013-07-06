package org.consulo.mybatis.module.extension;

import javax.swing.JComponent;

import org.consulo.module.extension.MutableModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisMutableModuleExtension extends MyBatisModuleExtension implements MutableModuleExtension<MyBatisModuleExtension>
{
	@NotNull
	private final MyBatisModuleExtension myModuleExtension;

	public MyBatisMutableModuleExtension(@NotNull String id, @NotNull Module module, @NotNull MyBatisModuleExtension moduleExtension)
	{
		super(id, module);
		myModuleExtension = moduleExtension;
		commit(myModuleExtension);
	}

	@Nullable
	@Override
	public JComponent createConfigurablePanel(@NotNull ModifiableRootModel modifiableRootModel, @Nullable Runnable runnable)
	{
		return null;
	}

	@Override
	public void setEnabled(boolean b)
	{
		myIsEnabled = b;
	}

	@Override
	public boolean isModified()
	{
		return myIsEnabled != myModuleExtension.isEnabled();
	}

	@Override
	public void commit()
	{
		myModuleExtension.commit(this);
	}
}
