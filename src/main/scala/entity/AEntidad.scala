package entity

/**Absract class that extends from Entidad
 *
 *
 */
abstract class AEntidad extends Entidad {
    private var name: String = ""
    private var hp: Int = 0
    private var defense: Int = 0
    private var weight: Int = 0

  /**This constructor of the abstract class, takes all before defined private values
   */
    def this(name:String,hp:Int,defense:Int,weight:Int) {
      this()
      this.name = name
      this.hp = hp
      this.defense = defense
      this.weight = weight
    }

    override def getName(): String ={
      this.name
    }

    override def getHp(): Int = {
      this.hp
    }

    override def getDefense(): Int = {
      this.defense
    }

    override def getWeight(): Int = {
      this.weight
    }

}
