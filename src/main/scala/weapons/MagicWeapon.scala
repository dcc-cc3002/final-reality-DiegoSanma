package weapons

import attributes.Attributes

class MagicWeapon (name:String,atkpoints:Int,weight:Int,magicpoints:Int,owner:Option[Attributes]=None)
  extends AMagicWeapon(name,atkpoints,weight, magicpoints, owner) {

}
