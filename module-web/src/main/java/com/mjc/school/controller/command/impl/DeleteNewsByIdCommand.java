package com.mjc.school.controller.command.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;


import java.util.Scanner;

public class DeleteNewsByIdCommand extends AbstractCommand<NewsDtoRequest, NewsDtoResponse, Long> implements Command {
    public DeleteNewsByIdCommand(BaseController controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_ID);
        System.out.println(getController().deleteById(Utils.inputLongNumber(getInput())));
    }
}
