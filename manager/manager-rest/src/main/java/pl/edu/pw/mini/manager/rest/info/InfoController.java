package pl.edu.pw.mini.manager.rest.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.manager.rest.info.service.InfoService;
import pl.gutowskid.manager.api.info.StatisticDto;

@RestController
public class InfoController {

    @Autowired
    private InfoService service;

    @RequestMapping(path = "/info/statistic", method = RequestMethod.GET, produces = "application/json")
    public StatisticDto getStatistic() {
        return service.getStatistic(ContextService.getContext().getUserId());
    }
}
