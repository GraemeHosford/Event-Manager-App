package graeme.hosford.eventmanager.data.common.entity

import com.google.firebase.firestore.DocumentSnapshot

interface EntityConverter<Entity> {

    fun convert(document: DocumentSnapshot): Entity

}