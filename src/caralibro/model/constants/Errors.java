package caralibro.model.constants;

import java.util.HashMap;
import java.util.Map;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class Errors {
	private static final Map<Integer,String> ERRORS_MAP = createErrorsMap();
	
	private static Map<Integer,String> createErrorsMap() {
		Map<Integer,String> errorsMap = new HashMap<Integer,String>();
		// General errors
		errorsMap.put(0,"General Errors: Success.");
		errorsMap.put(1,"General Errors: An unknown error occurred. Please resubmit the request.");
		errorsMap.put(2,"General Errors: Service temporarily unavailable.");
		errorsMap.put(3,"General Errors: Unknown method.");
		errorsMap.put(4,"General Errors: Application request limit reached.");
		errorsMap.put(5,"General Errors: Unauthorized source IP address.");
		errorsMap.put(6,"General Errors: This method must run on api.facebook.com.");
		errorsMap.put(7,"General Errors: This method must run on api-video.facebook.com.");
		errorsMap.put(8,"General Errors: This method requires an HTTPS connection.");
		errorsMap.put(9,"General Errors: User is performing too many actions.");
		errorsMap.put(10,"General Errors: Application does not have permission for this action.");
		errorsMap.put(11,"General Errors: This method is deprecated.");
		errorsMap.put(12,"General Errors: This API version is deprecated.");
		// Parameter Errors
		errorsMap.put(100, "Parameter Errors: Invalid parameter.");
		errorsMap.put(101, "Parameter Errors: Invalid API key.");
		errorsMap.put(102, "Parameter Errors: Session key invalid or no longer valid.");
		errorsMap.put(103, "Parameter Errors: Call_id must be greater than previous.");
		errorsMap.put(104, "Parameter Errors: Incorrect signature.");
		errorsMap.put(105, "Parameter Errors: The number of parameters exceeded the maximum for this operation.");
		errorsMap.put(110, "Parameter Errors: Invalid user id.");
		errorsMap.put(111, "Parameter Errors: Invalid user info field.");
		errorsMap.put(112, "Parameter Errors: Invalid user field.");
		errorsMap.put(113, "Parameter Errors: Invalid email.");
		errorsMap.put(120, "Parameter Errors: Invalid album id.");
		errorsMap.put(121, "Parameter Errors: Invalid photo id.");
		errorsMap.put(130, "Parameter Errors: Invalid feed publication priority.");
		errorsMap.put(140, "Parameter Errors: Invalid category.");
		errorsMap.put(141, "Parameter Errors: Invalid subcategory.");
		errorsMap.put(142, "Parameter Errors: Invalid title.");
		errorsMap.put(143, "Parameter Errors: Invalid description.");
		errorsMap.put(144, "Parameter Errors: Malformed JSON string.");
		errorsMap.put(150, "Parameter Errors: Invalid eid.");
		errorsMap.put(151, "Parameter Errors: Unknown city.");
		errorsMap.put(152, "Parameter Errors: Invalid page type.");
		// User Permission Errors
		errorsMap.put(200, "User Permission Errors: Permissions error.");
		errorsMap.put(201, "User Permission Errors: User not visible.");
		errorsMap.put(211, "User Permission Errors: Application has no developers.");
		errorsMap.put(220, "User Permission Errors: Album or albums not visible.");
		errorsMap.put(221, "User Permission Errors: API_EC_PERMISSION_PHOTO.");
		errorsMap.put(230, "User Permission Errors: Permissions disallow message to user.");
		errorsMap.put(240, "User Permission Errors: Desktop applications cannot set FBML for other users.");
		errorsMap.put(250, "User Permission Errors: Updating status requires the extended permission status_update.");
		errorsMap.put(260, "User Permission Errors: Modifying existing photos requires the extended permission photo_upload.");
		errorsMap.put(270, "User Permission Errors: Permissions disallow sms to user.");
		errorsMap.put(280, "User Permission Errors: Creating and modifying listings requires the extended permission create_listing.");
		errorsMap.put(281, "User Permission Errors: Managing notes requires the extended permission create_note.");
		errorsMap.put(282, "User Permission Errors: Managing shared items requires the extended permission share_item.");
		errorsMap.put(290, "User Permission Errors: Creating and modifying events requires the extended permission create_event.");
		errorsMap.put(291, "User Permission Errors: FBML Template isn\'t owned by your application.");
		errorsMap.put(291, "User Permission Errors: An application is only allowed to send LiveMessages to users who have accepted the TOS for that application.");
		errorsMap.put(299, "User Permission Errors: RSVPing to events requires the extended permission create_rsvp.");
		// Data Editing Errors
		errorsMap.put(300, "Data Editing Errors: Edit failure.");
		errorsMap.put(310, "Data Editing Errors: User data edit failure.");
		errorsMap.put(320, "Data Editing Errors: Photo edit failure.");
		errorsMap.put(321, "Data Editing Errors: Album is full.");
		errorsMap.put(322, "Data Editing Errors: Invalid photo tag subject.");
		errorsMap.put(323, "Data Editing Errors: Cannot tag photo already visible on Facebook.");
		errorsMap.put(324, "Data Editing Errors: Missing or invalid image file.");
		errorsMap.put(325, "Data Editing Errors: Too many unapproved photos pending.");
		errorsMap.put(326, "Data Editing Errors: Too many photo tags pending.");
		errorsMap.put(327, "Data Editing Errors: Input array contains a photo not in the album.");
		errorsMap.put(328, "Data Editing Errors: Input array has too few photos.");
		errorsMap.put(329, "Data Editing Errors: Template data must be a JSON-encoded dictionary, of the form {\'key-1\': \'value-1\', \'key-2\': \'value-2\', ...}.");
		errorsMap.put(330, "Data Editing Errors: Failed to set markup.");
		errorsMap.put(340, "Data Editing Errors: Feed publication request limit reached.");
		errorsMap.put(341, "Data Editing Errors: Feed action request limit reached.");
		errorsMap.put(342, "Data Editing Errors: Feed story title can have at most one href anchor.");
		errorsMap.put(343, "Data Editing Errors: Feed story title is too long.");
		errorsMap.put(344, "Data Editing Errors: Feed story title can have at most one fb:userlink and must be of the user whose action is being reported.");
		errorsMap.put(345, "Data Editing Errors: Feed story title rendered as blank.");
		errorsMap.put(346, "Data Editing Errors: Feed story body is too long.");
		errorsMap.put(347, "Data Editing Errors: Feed story photo could not be accessed or proxied.");
		errorsMap.put(348, "Data Editing Errors: Feed story photo link invalid.");
		errorsMap.put(350, "Data Editing Errors: Video file is too large.");
		errorsMap.put(351, "Data Editing Errors: Video file was corrupt or invalid.");
		errorsMap.put(351, "Data Editing Errors: Video file format is not supported.");
		errorsMap.put(360, "Data Editing Errors: Feed story title_data argument was not a valid JSON-encoded array.");
		errorsMap.put(361, "Data Editing Errors: Feed story title template either missing required parameters, or did not have all parameters defined in title_data array.");
		errorsMap.put(362, "Data Editing Errors: Feed story body_data argument was not a valid JSON-encoded array.");
		errorsMap.put(363, "Data Editing Errors: Feed story body template either missing required parameters, or did not have all parameters defined in body_data array.");
		errorsMap.put(364, "Data Editing Errors: Feed story photos could not be retrieved, or bad image links were provided.");
		errorsMap.put(365, "Data Editing Errors: The template for this story does not match any templates registered for this application.");
		errorsMap.put(366, "Data Editing Errors: One or more of the target ids for this story are invalid. They must all be ids of friends of the acting user.");
		errorsMap.put(370, "Data Editing Errors: The email address you provided is not a valid email address.");
		errorsMap.put(371, "Data Editing Errors: The email address you provided belongs to an existing account.");
		errorsMap.put(372, "Data Editing Errors: The birthday provided is not valid.");
		errorsMap.put(373, "Data Editing Errors: The password provided is too short or weak.");
		errorsMap.put(374, "Data Editing Errors: The login credential you provided is invalid.");
		errorsMap.put(375, "Data Editing Errors: Failed to send confirmation message to the specified login credential.");
		errorsMap.put(376, "Data Editing Errors: The login credential you provided belongs to an existing account.");
		errorsMap.put(377, "Data Editing Errors: Sorry, we were unable to process your registration.");
		errorsMap.put(378, "Data Editing Errors: Your password cannot be blank. Please try another.");
		errorsMap.put(379, "Data Editing Errors: Your password contains invalid characters. Please try another.");
		errorsMap.put(380, "Data Editing Errors: Your password must be at least 6 characters long. Please try another.");
		errorsMap.put(381, "Data Editing Errors: Your password should be more secure. Please try another.");
		errorsMap.put(382, "Data Editing Errors: Our automated system will not approve this name.");
		errorsMap.put(383, "Data Editing Errors: You must fill in all of the fields.");
		errorsMap.put(384, "Data Editing Errors: You must indicate your full birthday to register.");
		errorsMap.put(385, "Data Editing Errors: Please enter a valid email address.");
		errorsMap.put(386, "Data Editing Errors: The email address you entered has been disabled. Please contact disabled@facebook.com with any questions.");
		errorsMap.put(387, "Data Editing Errors: There was an error with your registration. Please try registering again.");
		errorsMap.put(388, "Data Editing Errors: Please select either Male or Female.");
		// Authentication Errors
		errorsMap.put(400, "Authentication Errors: Invalid email address.");
		errorsMap.put(401, "Authentication Errors: Invalid username or password.");
		errorsMap.put(402, "Authentication Errors: Invalid application auth sig.");
		errorsMap.put(403, "Authentication Errors: Invalid timestamp for authentication.");
		// Session Errors
		errorsMap.put(450, "Session Errors: Session key specified has passed its expiration time.");
		errorsMap.put(451, "Session Errors: Session key specified cannot be used to call this method.");
		errorsMap.put(452, "Session Errors: Session key invalid. This could be because the session key has an incorrect format, or because the user has revoked this session.");
		errorsMap.put(453, "Session Errors: A session key is required for calling this method.");
		errorsMap.put(454, "Session Errors: A session key must be specified when request is signed with a session secret.");
		errorsMap.put(455, "Session Errors: A session secret is not permitted to be used with this type of session key.");
		// Application Messaging Errors
		errorsMap.put(500, "Application Messaging Errors: Message contains banned content.");
		errorsMap.put(501, "Application Messaging Errors: Missing message body.");
		errorsMap.put(502, "Application Messaging Errors: Message is too long.");
		errorsMap.put(503, "Application Messaging Errors: User has sent too many messages.");
		errorsMap.put(504, "Application Messaging Errors: Invalid reply thread id.");
		errorsMap.put(505, "Application Messaging Errors: Invalid message recipient.");
		errorsMap.put(510, "Application Messaging Errors: Invalid poke recipient.");
		errorsMap.put(511, "Application Messaging Errors: There is a poke already outstanding.");
		errorsMap.put(512, "Application Messaging Errors: User is poking too fast.");
		// TODO: FQL Errors
		// TODO: Ref Errors
		// Application Integration Errors
		errorsMap.put(750, "Application Integration Errors: Unknown Facebook application integration failure.");
		errorsMap.put(751, "Application Integration Errors: Fetch from remote site failed.");
		errorsMap.put(752, "Application Integration Errors: Application returned no data. This may be expected or represent a connectivity error.");
		errorsMap.put(753, "Application Integration Errors: Application returned user had invalid permissions to complete the operation.");
		errorsMap.put(754, "Application Integration Errors: Application returned data, but no matching tag found. This may be expected.");
		errorsMap.put(755, "Application Integration Errors: The database for this object failed.");
		// TODO: Data Store API Errors
		// TODO: Mobile/SMS Errors
		// TODO: Application Information Errors
		// TODO: Batch API Errors
		// TODO: Events API Errors
		// TODO: Info Section Errors
		// TODO: LiveMessage Errors
		// TODO: Chat Errors
		// TODO: Facebook Page Errors
		// TODO: Facebook Links Errors
		// TODO: Facebook Notes Errors
		// Comment Errors
		errorsMap.put(1700, "Comment Errors: An unknown error has occurred.");
		errorsMap.put(1701, "Comment Errors: The specified post was too long.");
		errorsMap.put(1702, "Comment Errors: The comments database is down.");
		errorsMap.put(1703, "Comment Errors: The specified xid is not valid. xids can only contain letters, numbers, and underscores.");
		errorsMap.put(1704, "Comment Errors: The specified user is not a user of this application.");
		errorsMap.put(1705, "Comment Errors: There was an error during posting.");
		return errorsMap;
	}
		
	public static String getErrorMsg(int errorCode) {
		return ERRORS_MAP.get(errorCode);
	}
	
}
