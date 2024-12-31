package consulo.mybatis.module.extension;

import consulo.annotation.component.ExtensionImpl;
import consulo.localize.LocalizeValue;
import consulo.module.content.layer.ModuleExtensionProvider;
import consulo.module.content.layer.ModuleRootLayer;
import consulo.module.extension.ModuleExtension;
import consulo.module.extension.MutableModuleExtension;
import consulo.mybatis.icon.MyBatisIconGroup;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 22/01/2023
 */
@ExtensionImpl
public class MyBatisModuleExtensionProvider implements ModuleExtensionProvider<MyBatisModuleExtension>
{
	@Nonnull
	@Override
	public String getId()
	{
		return "mybatis";
	}

	@Nullable
	@Override
	public String getParentId()
	{
		return "java";
	}

	@Nonnull
	@Override
	public LocalizeValue getName()
	{
		return LocalizeValue.localizeTODO("iBatis/MyBatis");
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return MyBatisIconGroup.mybatis();
	}

	@Nonnull
	@Override
	public ModuleExtension<MyBatisModuleExtension> createImmutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new MyBatisModuleExtension(getId(), moduleRootLayer);
	}

	@Nonnull
	@Override
	public MutableModuleExtension<MyBatisModuleExtension> createMutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new MyBatisMutableModuleExtension(getId(), moduleRootLayer);
	}
}
