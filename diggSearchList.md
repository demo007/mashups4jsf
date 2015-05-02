This component gives you the ability to search in the Digg stories with your favorite search criteria.

| Component Attribute   | Required | Description | Default value |
|:----------------------|:---------|:------------|:--------------|
| searchQuery | true | The Digg search query. | NA |
| resultItemVar | true | The variable using which you will be able to access the Digg result item. Note that the Digg result item is of type (com.googlecode.mashups.services.digg.api.DiggSearchStoryResultItem). | NA |
| resultItemIndex | true | The Result item index. | NA |
| startResultIndex | false | The Digg stories search start result index. | 0 |
| resultSetSize | false | The Digg stories search result set size. | 10 |
| sortBy | false | Use this parameter if you want to sort the results. The possible values are: (promote\_date-desc, promote\_date-asc, submit\_date-desc, submit\_date-asc, digg\_count-desc, digg\_count-asc, date-desc, date-asc).  Note that all sort options are not always available on each endpoint. | NA |
| domain | false | Use this parameter if you want to filter the search to a specific domain. e.g. digg.com. | NA |