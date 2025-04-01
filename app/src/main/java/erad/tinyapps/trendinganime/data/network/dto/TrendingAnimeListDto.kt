package erad.tinyapps.trendinganime.data.network.dto

import erad.tinyapps.trendinganime.domain.model.AnimeData
import erad.tinyapps.trendinganime.domain.model.Attributes
import erad.tinyapps.trendinganime.domain.model.CoverImage
import erad.tinyapps.trendinganime.domain.model.PosterImage
import erad.tinyapps.trendinganime.domain.model.Titles

data class TrendingAnimeListResponseDto(
    val data: List<AnimeDataDto>
) {
    fun toModel(): List<AnimeData> =
        data.map { it.toModel() }
}

data class AnimeResponseDto(
    val data: AnimeDataDto
) {
    fun toModel(): AnimeData = data.toModel()
}

data class AnimeDataDto(
    val id: String,
    val type: String,
    val links: LinksDto,
    val attributes: AttributesDto,
    val relationships: RelationshipsDto
) {
    fun toModel(): AnimeData =
        AnimeData(
            id = id,
            attributes = attributes.toModel()
        )
}

data class LinksDto(
    val self: String
)

data class AttributesDto(
    val createdAt: String,
    val updatedAt: String,
    val slug: String?,
    val synopsis: String?,
    val coverImageTopOffset: Int,
    val titles: TitlesDto,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>,
    val averageRating: String?,
    val ratingFrequencies: Map<String,String>,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val subtype: String,
    val status: String,
    val tba: String?,
    val posterImage: PosterImageDto,
    val coverImage: CoverImageDto,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
    val nsfw: Boolean
) {
    fun toModel(): Attributes =
        Attributes(
            createdAt = createdAt,
            updatedAt = updatedAt,
            slug = slug,
            synopsis = synopsis,
            titles = titles.toModel(),
            canonicalTitle = canonicalTitle,
            abbreviatedTitles = abbreviatedTitles,
            ageRating = ageRatingGuide,
            coverImage = coverImage.toModel(),
            subtype = subtype,
            posterImage = posterImage.toModel(),
            episodeCount = episodeCount,
            episodeLength = episodeLength,
            showType = showType,
            ageRatingGuide = ageRatingGuide,
            favoritesCount = favoritesCount,
            popularityRank = popularityRank,
            status = status,
            tba = tba,
            endDate = endDate,
            startDate = startDate,
            userCount = userCount,
            averageRating = averageRating,
            ratingRank = ratingRank
        )
}

data class TitlesDto(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
) {
    fun toModel(): Titles =
        Titles(en)
}

data class PosterImageDto(
    val tiny: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String,
    val meta: MetaDto
) {
    fun toModel(): PosterImage =
        PosterImage(tiny, small, medium, large, original)
}

data class CoverImageDto(
    val tiny: String,
    val small: String,
    val large: String,
    val original: String,
    val meta: MetaDto
) {
    fun toModel(): CoverImage =
        CoverImage(tiny, small, large, original)
}

data class MetaDto(
    val dimensions: DimensionsDto
)

data class DimensionsDto(
    val tiny: DimensionDto?,
    val small: DimensionDto?,
    val medium: DimensionDto?,
    val large: DimensionDto?
)

data class DimensionDto(
    val width: Int?,
    val height: Int?
)

data class RelationshipsDto(
    val genres: RelationshipLinksDto,
    val categories: RelationshipLinksDto,
    val castings: RelationshipLinksDto,
    val installments: RelationshipLinksDto,
    val mappings: RelationshipLinksDto,
    val reviews: RelationshipLinksDto,
    val mediaRelationships: RelationshipLinksDto,
    val episodes: RelationshipLinksDto,
    val streamingLinks: RelationshipLinksDto,
    val animeProductions: RelationshipLinksDto,
    val animeCharacters: RelationshipLinksDto,
    val animeStaff: RelationshipLinksDto
)

data class RelationshipLinksDto(
    val links: LinksDto
)