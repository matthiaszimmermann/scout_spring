__include("scout-module.js");

scout.HtmlEnvironment = {
	// -------------------------------
	// IMPORTANT:
	// Some of the following constants are also defined in sizes.css. If you change
	// them, be sure to apply them at both places. (Remember to consider margins)
	// -------------------------------
	formRowHeight: 25, // @logical-grid-height
	formRowGap: 10,
	formColumnWidth: 350,
	formColumnGap: 22, // = 30 pixel actual form gap - fieldMandatoryIndicatorWidth
	smallColumnGap: 4,
	
	fieldLabelWidth: 80,
	fieldMandatoryIndicatorWidth: 8, // @mandatory-indicator-width
	fieldStatusWidth: 20 // @field-status-width
};
