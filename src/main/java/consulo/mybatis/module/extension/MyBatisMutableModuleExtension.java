package consulo.mybatis.module.extension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.disposer.Disposable;
import consulo.module.extension.MutableModuleExtension;
import consulo.roots.ModuleRootLayer;
import consulo.ui.Component;
import consulo.ui.annotation.RequiredUIAccess;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisMutableModuleExtension extends MyBatisModuleExtension implements MutableModuleExtension<MyBatisModuleExtension>
{
	public MyBatisMutableModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}

	@RequiredUIAccess
	@Nullable
	@Override
	public Component createConfigurationComponent(@Nonnull Disposable disposable, @Nonnull Runnable runnable)
	{
		return null;
	}

	@Override
	public void setEnabled(boolean b)
	{
		myIsEnabled = b;
	}

	@Override
	public boolean isModified(@Nonnull MyBatisModuleExtension extension)
	{
		return myIsEnabled != extension.isEnabled();
	}
}
