package test.polak.shay.ladbrokes.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


@Entity(tableName = "movies_table")
data class Movie(@PrimaryKey
                 val id: Int,
                 val video: Boolean,
                 val vote_average: Float,
                 val title: String,
                 val popularity: Float,
                 val poster_path: String,
                 val original_language: String,
                 val original_title: String,
                 val vote_count: Int,
                 val genre_ids: List<Int>,
                 val backdrop_path: String,
                 val adult: Boolean,
                 val overview: String,
                 val release_date: String
) : Parcelable
{

    private constructor(parcel: Parcel) : this(parcel.readInt(),
            parcel.readBoolean(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            listOf(),
            parcel.readString(),
            parcel.readBoolean(),
            parcel.readString(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeBoolean(video)
        parcel.writeFloat(vote_average)
        parcel.writeString(title)
        parcel.writeFloat(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeInt(vote_count)
        parcel.writeString(backdrop_path)
        parcel.writeBoolean(adult)
        parcel.writeString(overview)
        parcel.writeString(release_date)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {

        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
