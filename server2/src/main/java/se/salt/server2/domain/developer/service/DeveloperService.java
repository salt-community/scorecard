package se.salt.server2.domain.developer.service;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperResponses getAllDevelopers() {
        return developerRepository.findAll();
    }


}
