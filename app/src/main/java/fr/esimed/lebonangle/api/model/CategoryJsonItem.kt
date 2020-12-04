package fr.esimed.lebonangle.api.model

data class CategoryJsonItem(
    val id: Int,
    val name: String
){
    override fun toString(): String {
        return name
    }
}