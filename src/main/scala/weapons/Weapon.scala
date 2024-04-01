package weapons

/**An abstract class for a weapon that includes the methods used for
 * different types of weapons
 *
 * The abstract class is defined using the characteristics from thr trait Weapons
 *
 * @author Diego San Martin
 */

abstract class Weapon extends Weapons {
  /**Returns the name of the weapon
   *
   * The name of the weapon corresponds to a string, which could already belong to the
   * weapon or could have been given by someone
   *
   * @throws "This weapon has no name" if the weapon does not have name yet
   *
   */

  def nameGet(): Unit = {
    if (this.name == ""){
      println("This weapon has no name")
      return null
    }
    this.name
  }

  /**Renames the weapon to the name(named) of choice
   *
   * @param named the new name to be assigned to the weapon
   *
   * @throws This weapon has already been named $name$, if the weapon has already received a name
   */

 def rename(named: String): Unit ={
   if (this.name != ""){
     println(s"This weapon has already been named ${name}" )
     return null
   }
   this.name = named
   println(s"This weapon has been named ${name}")
 }

}
