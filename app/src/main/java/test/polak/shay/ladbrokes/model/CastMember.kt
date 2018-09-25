package test.polak.shay.ladbrokes.model

//data class CastMember(val cast_id: Int,
//                      val character: String,
//                      val credit_id: String,
//                      val name: String,
//                      val id: Int,
//                      val order: Int)


data class CastMember(val cast_id: Int,
                      val character: String,
                      val credit_id: String,
                      val name: String,
                      val id: Int,
                      val order: Int,
                      val gender: Int = 1,
                      val profile_path: String = "")



