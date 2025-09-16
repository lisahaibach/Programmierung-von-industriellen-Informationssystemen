package de.iu.ghostnet.controller;

import de.iu.ghostnet.entity.GhostNet;
import de.iu.ghostnet.entity.Status;
import de.iu.ghostnet.repository.GhostNetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nets")
public class GhostNetController {

    private final GhostNetRepository repo;

    public GhostNetController(GhostNetRepository repo) {
        this.repo = repo;
    }

    // Liste aller Netze
    @GetMapping
    public String list(Model model) {
        model.addAttribute("nets", repo.findAll());
        return "nets";  // lädt templates/nets.html
    }

    // Formular anzeigen
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("net", new GhostNet());
        return "net-form"; // lädt templates/net-form.html
    }

    // Neues Netz speichern
    @PostMapping
    public String save(@ModelAttribute GhostNet net) {
        net.setStatus(Status.GEMELDET);
        repo.save(net);
        return "redirect:/nets";
    }
}