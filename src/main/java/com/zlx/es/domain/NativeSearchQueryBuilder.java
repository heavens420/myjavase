//package com.zlx.es.domain;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.action.support.IndicesOptions;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.collapse.CollapseBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
//import org.elasticsearch.search.sort.SortBuilder;
//import org.springframework.util.CollectionUtils;
//
//public class NativeSearchQueryBuilder {
//    private QueryBuilder queryBuilder;
//    private QueryBuilder filterBuilder;
//    private List<SearchSourceBuilder.ScriptField> scriptFields = new ArrayList();
//    private List<SortBuilder> sortBuilders = new ArrayList();
////    private List<FacetRequest> facetRequests = new ArrayList();
//    private List<AbstractAggregationBuilder> aggregationBuilders = new ArrayList();
//    private HighlightBuilder highlightBuilder;
//    private Field[] highlightFields;
////    private Pageable pageable = Pageable.unpaged();
//    private String[] indices;
//    private String[] types;
//    private String[] fields;
////    private SourceFilter sourceFilter;
//    private CollapseBuilder collapseBuilder;
//    private List<SearchSourceBuilder.IndexBoost> indicesBoost;
//    private float minScore;
//    private boolean trackScores;
//    private Collection<String> ids;
//    private String route;
//    private SearchType searchType;
//    private IndicesOptions indicesOptions;
//    private String preference;
//
//    public NativeSearchQueryBuilder() {
//    }
//
//    public NativeSearchQueryBuilder withQuery(QueryBuilder queryBuilder) {
//        this.queryBuilder = queryBuilder;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withFilter(QueryBuilder filterBuilder) {
//        this.filterBuilder = filterBuilder;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withSort(SortBuilder sortBuilder) {
//        this.sortBuilders.add(sortBuilder);
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withScriptField(SearchSourceBuilder.ScriptField scriptField) {
//        this.scriptFields.add(scriptField);
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withCollapseField(String collapseField) {
//        this.collapseBuilder = new CollapseBuilder(collapseField);
//        return this;
//    }
//
//    public NativeSearchQueryBuilder addAggregation(AbstractAggregationBuilder aggregationBuilder) {
//        this.aggregationBuilders.add(aggregationBuilder);
//        return this;
//    }
//
////    public NativeSearchQueryBuilder withFacet(FacetRequest facetRequest) {
////        this.facetRequests.add(facetRequest);
////        return this;
////    }
//
//    public NativeSearchQueryBuilder withHighlightBuilder(HighlightBuilder highlightBuilder) {
//        this.highlightBuilder = highlightBuilder;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withHighlightFields(Field... highlightFields) {
//        this.highlightFields = highlightFields;
//        return this;
//    }
////
////    public NativeSearchQueryBuilder withIndicesBoost(List<IndexBoost> indicesBoost) {
////        this.indicesBoost = indicesBoost;
////        return this;
////    }
////
////    public NativeSearchQueryBuilder withPageable(Pageable pageable) {
////        this.pageable = pageable;
////        return this;
////    }
//
//    public NativeSearchQueryBuilder withIndices(String... indices) {
//        this.indices = indices;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withTypes(String... types) {
//        this.types = types;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withFields(String... fields) {
//        this.fields = fields;
//        return this;
//    }
//
////    public NativeSearchQueryBuilder withSourceFilter(SourceFilter sourceFilter) {
////        this.sourceFilter = sourceFilter;
////        return this;
////    }
//
//    public NativeSearchQueryBuilder withMinScore(float minScore) {
//        this.minScore = minScore;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withTrackScores(boolean trackScores) {
//        this.trackScores = trackScores;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withIds(Collection<String> ids) {
//        this.ids = ids;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withRoute(String route) {
//        this.route = route;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withSearchType(SearchType searchType) {
//        this.searchType = searchType;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withIndicesOptions(IndicesOptions indicesOptions) {
//        this.indicesOptions = indicesOptions;
//        return this;
//    }
//
//    public NativeSearchQueryBuilder withPreference(String preference) {
//        this.preference = preference;
//        return this;
//    }
//
//    public NativeSearchQuery build() {
//        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(this.queryBuilder, this.filterBuilder, this.sortBuilders, this.highlightBuilder, this.highlightFields);
//        nativeSearchQuery.setPageable(this.pageable);
//        nativeSearchQuery.setTrackScores(this.trackScores);
//        if (this.indices != null) {
//            nativeSearchQuery.addIndices(this.indices);
//        }
//
//        if (this.types != null) {
//            nativeSearchQuery.addTypes(this.types);
//        }
//
//        if (this.fields != null) {
//            nativeSearchQuery.addFields(this.fields);
//        }
//
//        if (this.sourceFilter != null) {
//            nativeSearchQuery.addSourceFilter(this.sourceFilter);
//        }
//
//        if (this.indicesBoost != null) {
//            nativeSearchQuery.setIndicesBoost(this.indicesBoost);
//        }
//
//        if (!CollectionUtils.isEmpty(this.scriptFields)) {
//            nativeSearchQuery.setScriptFields(this.scriptFields);
//        }
//
//        if (this.collapseBuilder != null) {
//            nativeSearchQuery.setCollapseBuilder(this.collapseBuilder);
//        }
//
//        if (!CollectionUtils.isEmpty(this.facetRequests)) {
//            nativeSearchQuery.setFacets(this.facetRequests);
//        }
//
//        if (!CollectionUtils.isEmpty(this.aggregationBuilders)) {
//            nativeSearchQuery.setAggregations(this.aggregationBuilders);
//        }
//
//        if (this.minScore > 0.0F) {
//            nativeSearchQuery.setMinScore(this.minScore);
//        }
//
//        if (this.ids != null) {
//            nativeSearchQuery.setIds(this.ids);
//        }
//
//        if (this.route != null) {
//            nativeSearchQuery.setRoute(this.route);
//        }
//
//        if (this.searchType != null) {
//            nativeSearchQuery.setSearchType(this.searchType);
//        }
//
//        if (this.indicesOptions != null) {
//            nativeSearchQuery.setIndicesOptions(this.indicesOptions);
//        }
//
//        if (this.preference != null) {
//            nativeSearchQuery.setPreference(this.preference);
//        }
//
//        return nativeSearchQuery;
//    }
//}
