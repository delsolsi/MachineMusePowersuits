package net.machinemuse.powersuits.item

import micdoodle8.mods.galacticraft.api.item.IBreathableArmor.EnumGearType
import micdoodle8.mods.galacticraft.api.item.IBreathableArmor
import net.machinemuse.powersuits.powermodule.misc.{ThaumGogglesModule, AirtightSealModule}
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import atomicscience.api.poison.Poison.ArmorType
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import atomicscience.api.poison.Poison
import net.machinemuse.api.ModuleManager
import net.machinemuse.utils.render.MuseRenderer
import thaumcraft.api.IGoggles
import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.nodes.IRevealer

class ItemPowerArmorHelmet(id: Int) extends ItemPowerArmor(id, 0, 0)
with IBreathableArmor
with IGoggles
with IRevealer {
  val iconpath = MuseRenderer.ICON_PREFIX + "armorhead"

  setUnlocalizedName("powerArmorHelmet")


  def getArmorType: Poison.ArmorType = ArmorType.HELM

  // IBreathableArmor
  def handleGearType(gearType: EnumGearType): Boolean = gearType eq EnumGearType.HELMET

  def canBreathe(helm: ItemStack, player: EntityPlayer, gearType: EnumGearType): Boolean =
    ModuleManager.itemHasActiveModule(helm, AirtightSealModule.AIRTIGHT_SEAL_MODULE)


  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IconRegister) {
    itemIcon = iconRegister.registerIcon(iconpath)
  }

  def showIngamePopups(itemstack: ItemStack, player: EntityLivingBase): Boolean =
    ModuleManager.itemHasActiveModule(itemstack, ThaumGogglesModule.MODULE_THAUM_GOGGLES)

  def showNodes(itemstack: ItemStack, player: EntityLivingBase): Boolean =
    ModuleManager.itemHasActiveModule(itemstack, ThaumGogglesModule.MODULE_THAUM_GOGGLES)
}