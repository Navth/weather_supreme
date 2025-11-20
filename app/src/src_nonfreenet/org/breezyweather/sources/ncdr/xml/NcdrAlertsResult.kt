package org.breezyweather.sources.ncdr.xml

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlKeyName
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
@XmlSerialName("feed", "http://www.w3.org/2005/Atom", "ncdr")
data class NcdrAlertsResult(
    val entries: List<Entry>? = null,
) {
    @Serializable
    @XmlSerialName("entry", "http://www.w3.org/2005/Atom", "ncdr")
    data class Entry(
        val id: Id,
        val title: Title? = null,
        val updated: Updated,
        val author: Author,
        val link: Link,
        val summary: Summary?,
        val category: Category?,
    ) {
        @Serializable
        @XmlSerialName("id", "", "ncdr")
        data class Id(
            @XmlValue(true) val value: String,
        )

        @Serializable
        @XmlSerialName("title", "", "ncdr")
        data class Title(
            @XmlValue(true) val value: String,
        )

        @Serializable
        @XmlSerialName("updated", "", "ncdr")
        data class Updated(
            @XmlValue(true) val value:
            @Serializable(DateSerializer::class)
            Date,
        )

        @Serializable
        @XmlSerialName("author", "http://www.w3.org/2005/Atom", "ncdr")
        data class Author(
            val name: Name,
        ) {
            @Serializable
            @XmlSerialName("name", "", "ncdr")
            data class Name(
                @XmlValue(true) val value: String,
            )
        }

        @Serializable
        @XmlSerialName("link", "", "ncdr")
        data class Link(
            @XmlKeyName("href", "", "ncdr") val href: String,
        )

        @Serializable
        @XmlSerialName("summary", "", "ncdr")
        data class Summary(
            @XmlValue(true) val value: String,
        )

        @Serializable
        @XmlSerialName("category", "", "ncdr")
        data class Category(
            @XmlKeyName("term", "", "ncdr") val term: String,
        )
    }
}
