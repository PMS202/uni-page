package com.github.ethancarter.unipage.web.autoconfigure;

import com.github.ethancarter.unipage.web.mvc.PaginationDataActionHandler;
import com.github.ethancarter.unipage.web.mvc.PaginationExportsActionHandler;
import com.github.ethancarter.unipage.web.mvc.PaginationInfoActionHandler;
import org.springframework.context.annotation.Import;

@Import({PaginationDataActionHandler.class, PaginationExportsActionHandler.class, PaginationInfoActionHandler.class})
public class PaginationActionHandlerConfiguration {

}
