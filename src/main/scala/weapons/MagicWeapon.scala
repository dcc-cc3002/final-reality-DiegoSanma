package weapons

import attributes.Attributes

class MagicWeapon (name:String,atkpoints:Int,weight:Int,owner:Option[Attributes],magicpoints:Int)
  extends AMagicWeapon(name,atkpoints,weight, owner, magicpoints) {

}
