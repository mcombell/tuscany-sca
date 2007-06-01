document.onmouseover = hideAllMenus;

/* Whenever you add a new tab, you need to add it to this list so that when 
 * the mouse rolls off the menu, it disapears. 
 * Make sure the first variable matches the 'name' variable specefied in
 * the project.xml file 
 */
function hideAllMenus() {
	changeObjectVisibility('sca', 'hidden');
	changeObjectVisibility('sdo', 'hidden');
	changeObjectVisibility('das', 'hidden');
}

function showMenu(eventObj, menu) {
	hideAllMenus();
	eventObj.cancelBubble = true;
    if(changeObjectVisibility(menu, 'visible')) {
		return true;
    } else {
		return false;
    }
}

function getStyleObject(objectId) {
    // cross-browser function to get an object's style object given its id
    if(document.getElementById && document.getElementById(objectId)) {
		// W3C DOM
		return document.getElementById(objectId).style;
    } else if (document.all && document.all(objectId)) {
		// MSIE 4 DOM
		return document.all(objectId).style;
    } else if (document.layers && document.layers[objectId]) {
		// NN 4 DOM.. note: this won't find nested layers
		return document.layers[objectId];
    } else {
		return false;
    }
} // getStyleObject

function changeObjectVisibility(objectId, newVisibility) {
    // get a reference to the cross-browser style object and make sure the object exists
    var styleObject = getStyleObject(objectId);
    if(styleObject) {
		styleObject.visibility = newVisibility;
		return true;
    } else {
		//we couldn't find the object, so we can't change its visibility
		return false;
    }
}
