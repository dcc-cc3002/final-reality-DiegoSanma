package exceptions.weaponexceptions

class InvalidWeaponTypeException(details:String) extends Exception(s"An invalid weapon type was given -- $details"){

}
