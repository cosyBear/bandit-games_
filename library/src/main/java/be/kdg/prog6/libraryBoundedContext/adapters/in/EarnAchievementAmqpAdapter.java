package be.kdg.prog6.libraryBoundedContext.adapters.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EarnAchievementAmqpAdapter {





    @RabbitListener(queues = "shipOutQueue" )
    public void shipmentCompleted(ShipmentCompletedDto order) {
        ShipmentCompletedCommand command = new ShipmentCompletedCommand(order.purchaseOrderId(), order.vesselNumber(), order.completionTime());
        useCase.shipDeparture(command);


    }





}
