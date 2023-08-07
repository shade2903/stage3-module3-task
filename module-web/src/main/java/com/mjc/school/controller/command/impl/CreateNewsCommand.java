package com.mjc.school.controller.command.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.Scanner;

public class CreateNewsCommand extends AbstractCommand<NewsDtoRequest, NewsDtoResponse, Long> implements Command  {

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }
    @Override
    public void execute() {
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest();
        System.out.println(MenuConstants.ENTER_TITLE);
        String title = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_CONTENT);
        String content = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_AUTHOR_ID);
        Long authorId = Utils.inputLongNumber(getInput());
        newsDtoRequest.setTitle(title);
        newsDtoRequest.setContent(content);
        newsDtoRequest.setAuthorId(authorId);
        System.out.println(getController().create(newsDtoRequest));
    }
}
