package weapons

class MagicWeapon(var name:String,var atkpoints:Int, var weight:Int, var magicpts:Int,var owner:Option[Either[Paladin,Either[Ninja,Either[Guerrero,MagoNegro]]]] extends MagicWeapons {

}
