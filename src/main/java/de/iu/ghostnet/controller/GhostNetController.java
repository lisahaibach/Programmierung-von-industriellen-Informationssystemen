package de.iu.ghostnet.controller;

import de.iu.ghostnet.entity.*;
import de.iu.ghostnet.repository.GhostNetRepository;
import de.iu.ghostnet.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nets")
public class GhostNetController {

    private final GhostNetRepository nets;
    private final PersonRepository persons;

    public GhostNetController(GhostNetRepository nets, PersonRepository persons) {
        this.nets = nets;
        this.persons = persons;
    }

    // Liste aller Netze
    @GetMapping
    public String list(Model model) {
        model.addAttribute("nets", nets.findAll());
        return "nets";
    }

    // Liste: noch zu bergen
    @GetMapping("/open")
    public String openList(Model model) {
        model.addAttribute("nets",
            nets.findByStatusAndSalvagerIsNull(Status.GEMELDET));
        return "nets-open";
    }

    // Formular anzeigen
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("net", new GhostNet());
        return "net-form";
    }

    // Neues Netz speichern (anonyme Meldung möglich)
    @PostMapping
    public String save(@ModelAttribute GhostNet net) {
        net.setStatus(Status.GEMELDET);
        nets.save(net);
        return "redirect:/nets";
    }

    // ==== Aktionen ====

    // (2) "Ich berge das": einfache Variante – neue Person anlegen + zuweisen
    @PostMapping("/{id}/claim")
    public String claim(@PathVariable Long id,
                        @RequestParam String name,
                        @RequestParam String phone) {
        GhostNet net = nets.findById(id).orElseThrow();
        Person p = new Person();
        p.setName(name);
        p.setPhone(phone);
        p.setSalvager(true);
        persons.save(p);

        net.setSalvager(p);
        net.setStatus(Status.BERGUNG_BEVORSTEHEND);
        nets.save(net);
        return "redirect:/nets";
    }

    // (4) "Geborgen"
    @PostMapping("/{id}/recovered")
    public String recovered(@PathVariable Long id) {
        GhostNet net = nets.findById(id).orElseThrow();
        net.setStatus(Status.GEBORGEN);
        nets.save(net);
        return "redirect:/nets";
    }

    // (7) "Verschollen"
    @PostMapping("/{id}/missing")
    public String missing(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam String phone) {
        GhostNet net = nets.findById(id).orElseThrow();
        net.setStatus(Status.VERSCHOLLEN);
        // wer die Meldung gemacht hat, speichern wir hier als Reporter:
        net.setReporterName(name);
        net.setReporterPhone(phone);
        nets.save(net);
        return "redirect:/nets";
    }
}