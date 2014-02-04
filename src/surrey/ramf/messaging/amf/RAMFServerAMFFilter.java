package surrey.ramf.messaging.amf;

import surrey.ramf.exception.MessagingException;
import surrey.ramf.logging.LogWriter;
import surrey.ramf.messaging.amfr.RemotingMessage;
import surrey.ramf.messaging.server.AMFServiceContext;
import surrey.ramf.messaging.server.Endpoint;


/**
 * Last filter in the default endpoint chain. This filter calls all next filters
 * before routing the message to the server. That means any filters after this
 * one will not receive the response.
 * 
 * @author Surrey
 * 
 */
public class RAMFServerAMFFilter extends BaseAMFFilter {

	@Override
	public void invoke(AMFServiceContext context) {
		nextFilter(context);

		AMFMessageBody responseBody = new AMFMessageBody();
		context.setResponseBody(responseBody);

		AMFMessageBody messageBody = context.getRequestBody();

		Endpoint endpoint = context.getEndpoint();

		RemotingMessage remotingMessage = (RemotingMessage) messageBody.getData();
		try {
			responseBody.setData(endpoint.routeMessageToService(remotingMessage));
		} catch (MessagingException e) {
			LogWriter.error(getClass(), "Failed to route message to service: " + e, e);
			ErrorMessage errorBody = new ErrorMessage("Service destination could not be reached",
					remotingMessage.getDestination(), remotingMessage.getOperation(), remotingMessage.getParameters());
			responseBody.setData(errorBody);
		}
		responseBody.setResponseURI(messageBody.getResponseURI());
		if (responseBody.getData() instanceof ErrorMessage) {
			responseBody.setResponseURI(responseBody.getResponseURI() + AMFMessageBody.STATUS_METHOD);
		} else {
			responseBody.setResponseURI(responseBody.getResponseURI() + AMFMessageBody.RESULT_METHOD);
		}
		responseBody.setTargetURI(responseBody.getResponseURI());
	}
}
