package consulo.mybatis.module.extension;

import org.jetbrains.annotations.NotNull;
import consulo.module.extension.MutableModuleExtension;
import consulo.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisMutableModuleExtension extends MyBatisModuleExtension implements MutableModuleExtension<MyBatisModuleExtension>
{
	public MyBatisMutableModuleExtension(@NotNull String id, @NotNull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
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
