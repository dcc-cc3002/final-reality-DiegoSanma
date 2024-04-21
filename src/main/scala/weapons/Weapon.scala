package weapons

import attributes.Attributes

class Weapon(name:String,atkpoints:Int,weight:Int,owner:Option[Attributes])
  extends AWeapon(name,atkpoints,weight,owner) {

}
